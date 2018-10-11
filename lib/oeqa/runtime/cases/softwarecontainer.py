import os

from oeqa.oetest import oeRuntimeTest
from oeqa.runtime.case import OERuntimeTestCase
from oeqa.utils.decorators import skipUnlessPassed


class SoftwareContainerTest(OERuntimeTestCase):
    def test_softwarecontainer_installed(self):
        (status, _) = self.target.run('rpm -qa | grep softwarecontainer')
        self.assertEqual(status, 0, "softwarecontainer package is not installed")

    @skipUnlessPassed("test_softwarecontainer_installed")
    def test_softwarecontainer_running(self):
        (status, _) = self.target.run('ps -ef | grep softwarecontainer-agent')
        self.assertEqual(status, 0, msg="No softwarecontainer-agent process running, ps output: %s" %
                         self.target.run(oeRuntimeTest.pscmd)[1])

    @skipUnlessPassed("test_softwarecontainer_running")
    def test_softwarecontainer_restart(self):
        (status, output) = self.target.run('systemctl restart softwarecontainer-agent')
        self.assertEqual(status, 0, "Couldn't restart softwarecontainer: %d %s" % (status, output))

    @skipUnlessPassed("test_softwarecontainer_installed")
    def test_lib_exists(self):
        lib_file = "/usr/lib/libsoftwarecontainer.so.0"

        status = os.path.isfile(lib_file)
        self.assertEqual(status, 0, "Couldn't find libsoftwarecontainer.so.0")

        status = os.access(lib_file, os.R_OK)
        self.assertEqual(status, 0, "Couldn't access libsoftwarecontainer.so.0")

    @skipUnlessPassed("test_softwarecontainer_installed")
    def test_dbus_config_exists(self):
        dbus_config_file = "/etc/dbus-1/system.d/softwarecontainer-agent.conf"

        status = os.path.isfile(dbus_config_file)
        self.assertEqual(status, 0, "Couldn't find %s" % dbus_config_file)

        status = os.access(dbus_config_file, os.R_OK)
        self.assertEqual(status, 0, "Couldn't access %s" % dbus_config_file)
