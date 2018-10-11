import os

from oeqa.oetest import oeRuntimeTest
from oeqa.runtime.case import OERuntimeTestCase
from oeqa.utils.decorators import skipUnlessPassed


class NodeStateManagerDaemonTest(OERuntimeTestCase):
    def test_nsm_installed(self):
        (status, _) = self.target.run('rpm -qa | grep node-state-manager')
        self.assertEqual(status, 0, "node-state-manager package is not installed")

        # nodestatemanager-daemon is disabled by default, so start it first
        (status, _) = self.target.run('systemctl start nodestatemanager-daemon')
        self.assertEqual(status, 0, "Couldn't start nodestatemanager-daemon")

    @skipUnlessPassed("test_nsm_installed")
    def test_nsm_running(self):
        (status, _) = self.target.run('ps -ef | grep NodeStateManager')
        self.assertEqual(status, 0, "No NodeStateManager process running, ps output: %s" %
                         self.target.run(oeRuntimeTest.pscmd)[1])

    @skipUnlessPassed("test_nsm_running")
    def test_nsm_daemon_restart(self):
        (status, output) = self.target.run('systemctl restart nodestatemanager-daemon')
        self.assertEqual(status, 0, "Couldn't restart node-state-manager: %d %s" % (status, output))

    @skipUnlessPassed("test_nsm_installed")
    def test_dbus_config_exists(self):
        dbus_config = "/etc/dbus-1/system.d/org.genivi.NodeStateManager.LifeCycleControl.service"

        status = os.path.isfile(dbus_config)
        self.assertEqual(status, 0, "Couldn't find %s" % dbus_config)

        status = os.access(dbus_config, os.R_OK)
        self.assertEqual(status, 0, "Couldn't access %s" % dbus_config)
