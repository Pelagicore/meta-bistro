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
SRCREV = "5bcddca8ebdf546d8e86421f71b2412da8146c64"

inherit meson systemd

DEPENDS += "glibmm gdbus-codegen-glibmm-native"

S = "${WORKDIR}/git"

PACKAGECONFIG ??= " \
    msd_id_source \
    scard_id_source \
"

PACKAGECONFIG[msd_id_source] = "-Dmsd_id_source=true,-Dmsd_id_source=false"
PACKAGECONFIG[scard_id_source] = "-Dscard_id_source=true,-Dscard_id_source=false,pcsc-lite,pcsc-lite ccid"

FILES_${PN} = " \
    ${bindir}/uimcli \
    ${bindir}/user-identification-manager \
    ${datadir}/dbus-1 \
    ${systemd_system_unitdir} \
"

SYSTEMD_SERVICE_${PN} = "user-identification-manager.service"
SYSTEMD_AUTO_ENABLE = "disable"
