FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

# Upstream pr: https://github.com/GENIVI/dlt-daemon/pull/129
# Remove dlt-daemon with fix is used by meta-ivi.
SRC_URI += "file://0001-Do-not-install-systemd-service-files-for-binaries-th.patch"
SYSTEMD_SERVICE_${PN}_remove = "dlt-system.service"
SYSTEMD_SERVICE_${PN}-systemd_remove = "dlt-adaptor-udp.service"
