#
#   Copyright (C) 2015 Pelagicore AB
#
#   SPDX-License-Identifier: MIT
#
DESCRIPTION = "PEAK CAN Bus Driver" 
HOMEPAGE = "http://www.peak-system.com/fileadmin/media/linux/index.htm"
LICENSE = "GPLv2" 
LIC_FILES_CHKSUM = "file://Documentation/COPYING;md5=94d55d512a9ba36caa9b7df079bae19f" 
PR = "r0" 
SRC_URI = "http://www.peak-system.com/fileadmin/media/linux/files/peak-linux-driver-${PV}.tar.gz" 
SRC_URI[md5sum] = "a3e9e355c7055d5a07ce5d3ebca99132"
SRC_URI[sha256sum] = "dfe57d390918eaf949c7116d0639537e1f08704d8cb4ef55f63f8e8131e7c2f0"

inherit module

EXTRA_OEMAKE = "NET=NETDEV_SUPPORT KERNEL_LOCATION=${STAGING_KERNEL_DIR}"

do_install() {
  oe_runmake install DESTDIR=${D}
}

PACKAGES = "\
    ${PN}       \
    ${PN}-tools \
"

FILES_${PN} += "\
    ${libdir}            \
    ${libdir}/*${SOLIBS} \
"

FILES_${PN}-tools += "\
    ${bindir}/pcan_make_devices \
"

