#
#   Copyright (C) 2015 Pelagicore AB
#   All rights reserved.
#
DESCRIPTION = "Swaplogger"
HOMEPAGE = "https://github.com/mer-tools/swaplogger"
DEPENDS = "virtual/egl"
PR = "r0"

inherit autotools

LICENSE = "nokia"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d25f5ab7fee8539cd6db2dcf074cece6"

SRC_URI = " \
       git://github.com/mer-tools/swaplogger.git;branch=master \
       file://fix_makefile.patch \
       "

SRCREV = "b06b5476dae2ee56688e3d5f7e0bf4987848d476"

S = "${WORKDIR}/git/"

CFLAGS += "-DLINUX=1 -DEGL_API_FB=1 -shared -fPIC"

do_install() {
	mkdir -p ${D}/${libdir}
	mkdir -p ${D}/${bindir}
	install -m 0644 ${S}/swaplogger.s* ${D}/${libdir}/
	install -m 0755 ${S}/swaplogger ${D}/${bindir}/
}

INSANE_SKIP_${PN} += "dev-so"
FILES_${PN} += "${libdir}/*.so.*"
FILES_${PN} += "${libdir}/*.so*"
