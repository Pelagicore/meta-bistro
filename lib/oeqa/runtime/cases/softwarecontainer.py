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
        lib_file = "/usr/lib/libsoftwarecontainer.so.0.18.0"

        (status, output) = self.target.run('stat %s' % lib_file)
        self.assertEqual(status, 0, "Couldn't find %s" % lib_file)
        self.assertTrue("file" in output, "%s is not a file" % lib_file)

    @skipUnlessPassed("test_softwarecontainer_install")
    def test_dbus_config_exists(self):
        dbus_config_file = "/etc/dbus-1/system.d/softwarecontainer-agent.conf"

        (status, output) = self.target.run('stat %s' % dbus_config_file)
        self.assertEqual(status, 0, "Couldn't find %s" % dbus_config_file)
        self.assertTrue("file" in output, "%s is not a file" % dbus_config_file)

    @skipUnlessPassed("test_softwarecontainer_install")
    def test_softwarecontainer_agent(self):
        # 1- Create a software container
        (status, output) = self.target.run('dbus-send --system --print-reply \
                         --dest=com.pelagicore.SoftwareContainerAgent \
                         /com/pelagicore/SoftwareContainerAgent \
                         com.pelagicore.SoftwareContainerAgent.Create \
                         string:\'[{"writeBufferEnabled": false}]\'')

        self.assertEqual(status, 0, "Create: %d %s" % (status, output))

        container_path = "/tmp/container/SC-0/gateways"

        (status, output) = self.target.run('stat %s' % container_path)
        self.assertEqual(status, 0, "Couldn't find %s" % container_path)
        self.assertTrue("directory" in output, "%s is not a directory" % container_path)

        # 2- Bind mount a host directory on to container directory
        swc_dir = "/home/root/softwarecontainer"

        (status, _) = self.target.run('mkdir %s' % swc_dir)

        self.assertEqual(status, 0, "Couldn't create %s" % swc_dir)

        (status, output) = self.target.run('dbus-send --system --print-reply \
                         --dest=com.pelagicore.SoftwareContainerAgent \
                         /com/pelagicore/SoftwareContainerAgent \
                         com.pelagicore.SoftwareContainerAgent.BindMount \
                         int32:0 string:\'/home/root/softwarecontainer\' \
                         string:\'/app\' boolean:false')

        bindmount_path = "/tmp/container/SC-0/gateways/app"

        (status, output) = self.target.run('stat %s' % bindmount_path)
        self.assertEqual(status, 0, "Couldn't find %s" % bindmount_path)
        self.assertTrue("directory" in output, "%s is not a directory" % bindmount_path)

        # 3- Execute a command that creates a file inside the container
        (status, output) = self.target.run('dbus-send --system --print-reply \
                         --dest=com.pelagicore.SoftwareContainerAgent \
                         /com/pelagicore/SoftwareContainerAgent \
                         com.pelagicore.SoftwareContainerAgent.Execute \
                         int32:0 string:\'touch test.txt\' string:\'/app\' \
                         string:\'\' dict:string:string:\'\'')

        test_file = "/tmp/container/SC-0/gateways/app/test.txt"

        (status, output) = self.target.run('stat %s' % test_file)
        self.assertEqual(status, 0, "Couldn't find %s" % test_file)
        self.assertTrue("file" in output, "%s is not a file" % test_file)

        # 4- Destroy the container
        (status, output) = self.target.run('dbus-send --system --print-reply \
                         --dest=com.pelagicore.SoftwareContainerAgent \
                         /com/pelagicore/SoftwareContainerAgent \
                         com.pelagicore.SoftwareContainerAgent.Destroy int32:0')

        container_path = "/tmp/container/SC-0/gateways"

        (status, output) = self.target.run('stat %s' % container_path)
        self.assertNotEqual(status, 0, "Failed to remove %s" % container_path)
