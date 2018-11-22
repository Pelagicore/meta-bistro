#
#   Copyright (C) 2018 Luxoft Sweden AB
#
#   SPDX-License-Identifier: MIT
#
DESCRIPTION = "Developer's Image Library"
HOMEPAGE = "http://openil.sourceforge.net/"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${WORKDIR}/DevIL/DevIL/COPYING;md5=2f628ba2d30e3cd13fc76410ce6d3db2"

SRC_URI = "https://sourceforge.net/projects/openil/files/DevIL/${PV}/DevIL-${PV}.tar.gz"
SRC_URI[md5sum] = "4d8c21aa4822ac86d77e44f8d7c9becd"
SRC_URI[sha256sum] = "0075973ee7dd89f0507873e2580ac78336452d29d34a07134b208f44e2feb709"

S = "${WORKDIR}/DevIL/DevIL"

inherit pkgconfig cmake

FILES_${PN}-dev = "${includedir} ${libdir}/*.la \
                ${libdir}/*.o ${libdir}/pkgconfig ${datadir}/pkgconfig \
                ${datadir}/aclocal ${base_libdir}/*.o \
                ${libdir}/${BPN}/*.la ${base_libdir}/*.la"
FILES_${PN} += "${FILES_SOLIBSDEV}"
