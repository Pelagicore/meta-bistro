#
# Copyright (C) 2019 Luxoft Sweden AB
# SPDX-License-Identifier: MIT
#

DESCRIPTION = "User Identification Manager D-Bus service"
HOMEPAGE = "https://github.com/Pelagicore/user-identification-manager"
LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=815ca599c9df247a0c7f619bab123dad"
PV = "0.1.0+git${SRCPV}"
PR = "r0"

SRC_URI = "git://github.com/Pelagicore/user-identification-manager.git;branch=master;protocol=https"
SRCREV = "4ada21266f72b4e98337dfc787b2906427f7ed20"

inherit meson systemd

DEPENDS += "glibmm gdbus-codegen-glibmm-native"

S = "${WORKDIR}/git"

FILES_${PN} = " \
    ${bindir}/uimcli \
    ${bindir}/user-identification-manager \
    ${datadir}/dbus-1 \
    ${systemd_system_unitdir} \
"

SYSTEMD_SERVICE_${PN} = "user-identification-manager.service"
SYSTEMD_AUTO_ENABLE = "disable"
