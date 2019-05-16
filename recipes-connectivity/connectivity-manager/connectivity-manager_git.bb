#
# Copyright (C) 2019 Luxoft Sweden AB
# SPDX-License-Identifier: MIT
#

DESCRIPTION = "Connectivity Manager D-Bus service"
HOMEPAGE = "https://github.com/Pelagicore/connectivity-manager"
LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=815ca599c9df247a0c7f619bab123dad"
PV = "0.1.0+git${SRCPV}"
PR = "r0"

SRC_URI = "git://github.com/Pelagicore/connectivity-manager.git;branch=master;protocol=https"
SRCREV = "b03d7d88018b29a81c86a7a7c6f5d876772648b3"

inherit meson systemd

DEPENDS += "glibmm gdbus-codegen-glibmm-native"
RDEPENDS_${PN} = "connman"

S = "${WORKDIR}/git"

FILES_${PN} = " \
    ${bindir}/cmcli \
    ${bindir}/connectivity-manager \
    ${datadir}/dbus-1 \
    ${systemd_system_unitdir} \
"

SYSTEMD_SERVICE_${PN} = "connectivity-manager.service"
SYSTEMD_AUTO_ENABLE = "disable"
