import os

from oeqa.oetest import oeRuntimeTest
from oeqa.runtime.case import OERuntimeTestCase
from oeqa.utils.decorators import skipUnlessPassed


class SoftwareContainerTest(OERuntimeTestCase):
    def test_softwarecontainer_install(self):
        (status, _) = self.target.run('rpm -qa | grep softwarecontainer')
        self.assertEqual(status, 0, "softwarecontainer package is not installed")

    @skipUnlessPassed("test_softwarecontainer_install")
    def test_softwarecontainer_running(self):
        (status, _) = self.target.run('ps -ef | grep softwarecontainer-agent')
        self.assertEqual(status, 0, msg="No softwarecontainer-agent process running, \
                         ps output: %s" % self.target.run(oeRuntimeTest.pscmd)[1])

    @skipUnlessPassed("test_softwarecontainer_running")
    def test_softwarecontainer_restart(self):
        (status, output) = self.target.run('systemctl restart softwarecontainer-agent')
        self.assertEqual(status, 0, "Couldn't restart softwarecontainer: %d %s" % (status, output))

    @skipUnlessPassed("test_softwarecontainer_install")
    def test_lib_exists(self):
        lib_file = "/usr/lib/libsoftwarecontainer.so.0"

        (status, output) = self.target.run('stat %s' % lib_file)
        self.assertEqual(status, 0, "Couldn't find %s" % lib_file)
        self.assertTrue("symbolic link" in output, "%s is not a symbolic link" % lib_file)

    @skipUnlessPassed("test_softwarecontainer_install")
    def test_dbus_config_exists(self):
        dbus_config_file = "/etc/dbus-1/system.d/softwarecontainer-agent.conf"

        (status, output) = self.target.run('stat %s' % dbus_config_file)
        self.assertEqual(status, 0, "Couldn't find %s" % dbus_config_file)
        self.assertTrue("file" in output, "%s is not a file" % dbus_config_file)

    @skipUnlessPassed("test_softwarecontainer_install")
    def test_softwarecontainer_agent(self):

        # Test creating a container
        def test_create_container(self, expected_container_path):
            (status, output) = self.target.run('dbus-send --system --print-reply \
                             --dest=com.pelagicore.SoftwareContainerAgent \
                             /com/pelagicore/SoftwareContainerAgent \
                             com.pelagicore.SoftwareContainerAgent.Create \
                             string:\'[{"writeBufferEnabled": false}]\'')

            self.assertEqual(status, 0, "Create: %d %s" % (status, output))

            (status, output) = self.target.run('stat %s' % expected_container_path)
            self.assertEqual(status, 0, "Couldn't find %s" % expected_container_path)
            self.assertTrue("directory" in output, "%s is not a directory" % expected_container_path)

        # 1a- Create 1st software container
        expected_container_path = "/tmp/container/SC-0/gateways"
        test_create_container(self, expected_container_path)

        # 1b- Create 2nd softwarecontainer
        expected_container_path = "/tmp/container/SC-1/gateways"
        test_create_container(self, expected_container_path)

        # Test bind mounting a host directory on a container
        def test_bind_mount(self, sc_id, host_path, container_path):
            (status, _) = self.target.run('mkdir -p %s' % host_path)

            self.assertEqual(status, 0, "Couldn't create %s" % host_path)

            (status, output) = self.target.run('dbus-send --system --print-reply \
                             --dest=com.pelagicore.SoftwareContainerAgent \
                             /com/pelagicore/SoftwareContainerAgent \
                             com.pelagicore.SoftwareContainerAgent.BindMount \
                             int32:' + str(sc_id) + ' string:\'' + host_path + '\' \
                             string:\'' + container_path + '\' boolean:false')

            bindmount_path = "/tmp/container/SC-" + str(sc_id) + "/gateways" + container_path

            (status, output) = self.target.run('stat %s' % bindmount_path)
            self.assertEqual(status, 0, "Couldn't find %s" % bindmount_path)
            self.assertTrue("directory" in output, "%s is not a directory" % bindmount_path)

        host_path = "/home/root/softwarecontainer"
        container_path = "/app"
        # 2a- Bind mount a host directory on to 1st container directory
        test_bind_mount(self, 0, host_path, container_path)

        # 2b- Bind mount a host directory on to 2nd container directory
        test_bind_mount(self, 1, host_path, container_path)

        # Test execute a command inside a container
        def test_execute_createfile(self, sc_id, container_path):
            (status, output) = self.target.run('dbus-send --system --print-reply \
                             --dest=com.pelagicore.SoftwareContainerAgent \
                             /com/pelagicore/SoftwareContainerAgent \
                             com.pelagicore.SoftwareContainerAgent.Execute \
                             int32:' + str(sc_id) + ' string:\'touch test.txt\' string:\'' + container_path + '\' \
                             string:\'\' dict:string:string:\'\'')

            test_file = "/tmp/container/SC-" + str(sc_id) + "/gateways" + container_path + "/test.txt"

            (status, output) = self.target.run('stat %s' % test_file)
            self.assertEqual(status, 0, "Couldn't find %s" % test_file)
            self.assertTrue("file" in output, "%s is not a file" % test_file)

        container_path = "/app"
        # 3a- Execute a command that creates a file inside the 1st container
        test_execute_createfile(self, 0, container_path)

        # 3b- Execute a command that creates a file inside the 2nd container
        test_execute_createfile(self, 1, container_path)

        # Test destroy a container
        def test_destroy(self, sc_id):
            (status, output) = self.target.run('dbus-send --system --print-reply \
                             --dest=com.pelagicore.SoftwareContainerAgent \
                             /com/pelagicore/SoftwareContainerAgent \
                             com.pelagicore.SoftwareContainerAgent.Destroy int32:' + str(sc_id))

            container_path = "/tmp/container/SC-" + str(sc_id) + "/gateways"

            (status, output) = self.target.run('stat %s' % container_path)
            self.assertNotEqual(status, 0, "Failed to remove %s" % container_path)

        # 4a- Destroy 1st container
        test_destroy(self, 0)

        # 4b- Destroy 2nd container
        test_destroy(self, 1)
