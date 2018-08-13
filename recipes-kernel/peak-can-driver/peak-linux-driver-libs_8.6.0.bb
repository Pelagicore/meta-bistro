#
#   Copyright (C) 2015 Pelagicore AB
#   Copyright (C) 2018 Luxoft Sweden AB
#
#   SPDX-License-Identifier: MIT
#
DESCRIPTION = "PEAK CAN Bus Linux API"
HOMEPAGE = "http://www.peak-system.com/fileadmin/media/linux/index.htm"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${WORKDIR}/peak-linux-driver-${PV}/Documentation/COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"

SRC_URI = "http://www.peak-system.com/fileadmin/media/linux/files/peak-linux-driver-${PV}.tar.gz"
SRC_URI[md5sum] = "f62d94c721aa6a83316fcb4232631a64"
SRC_URI[sha256sum] = "62fa0ebde620a436816546bc1f82feb663a32f20db26e8889b7204b9686f5776"

DEPENDS = "popt"

RDEPENDS_${PN} = "bash"

S = "${WORKDIR}/peak-linux-driver-${PV}/lib"

INSANE_SKIP_${PN} = "ldflags"

EXTRA_OEMAKE = "NET=NETDEV_SUPPORT"

do_install() {
    oe_runmake install DESTDIR=${D}
}

FILES_${PN} += "\
    ${includedir} \
    ${libdir} \
"
