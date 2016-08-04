#
#   Copyright (C) 2015 Pelagicore AB
#
inherit systemd

SYSTEMD_AUTO_ENABLE = "disable"
SYSTEMD_PACKAGES = "${PN}-systemd"
SYSTEMD_SERVICE_${PN}-systemd = "cinematicexperience.service"

PACKAGES += "${PN}-systemd"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += " \
    file://cinematicexperience.service \
    "
do_install_append() {
    install -D -m 0644 ${WORKDIR}/cinematicexperience.service ${D}${systemd_unitdir}/system/cinematicexperience.service
}
