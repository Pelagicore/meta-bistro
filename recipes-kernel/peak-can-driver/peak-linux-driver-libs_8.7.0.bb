#
#   Copyright (C) 2015 Pelagicore AB
#   Copyright (C) 2018-2019 Luxoft Sweden AB
#
#   SPDX-License-Identifier: MIT
#
DESCRIPTION = "PEAK CAN Bus Linux API"
HOMEPAGE = "http://www.peak-system.com/fileadmin/media/linux/index.htm"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${WORKDIR}/peak-linux-driver-${PV}/Documentation/COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"

SRC_URI = "http://www.peak-system.com/fileadmin/media/linux/files/peak-linux-driver-${PV}.tar.gz"
SRC_URI[md5sum] = "a10552ac9e6f64a250ec85bc8bef2d22"
SRC_URI[sha256sum] = "6d1ad9e6feb75719feb926bf1c78caeaa84663855945866e749d92cc83758b73"

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
