import os

from oeqa.oetest import oeRuntimeTest
from oeqa.runtime.case import OERuntimeTestCase
from oeqa.utils.decorators import skipUnlessPassed


class DLTDaemonTest(OERuntimeTestCase):
    def test_dlt_installed(self):
        (status, _) = self.target.run('rpm -qa | grep dlt-daemon')
        self.assertEqual(status, 0, "dlt-daemon package is not installed")

    @skipUnlessPassed("test_dlt_installed")
    def test_dlt_daemon_running(self):
        (status, _) = self.target.run('ps -ef | grep dlt-daemon')
        self.assertEqual(status, 0, msg="No dlt-daemon process running, ps output: %s" %
                         self.target.run(oeRuntimeTest.pscmd)[1])

    @skipUnlessPassed("test_dlt_daemon_running")
    def test_dlt_daemon_restart(self):
        (status, output) = self.target.run('systemctl restart dlt')
        self.assertEqual(status, 0, "Couldn't restart dlt: %d %s" % (status, output))
