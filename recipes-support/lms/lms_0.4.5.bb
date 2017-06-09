#
#   Copyright (C) 2015 Pelagicore AB

DESCRIPTION = "Lightweight media scanner meant to be used in not-so-powerful devices, like embedded systems or old machines."
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=a6f89e2100d9b6cdffcea4f398e37343"
HOMEPAGE = "https://github.com/profusion/lightmediascanner"

inherit autotools pkgconfig gettext

DEPENDS = "sqlite3"

PR = "r0"

EXTRA_OECONF += "--program-prefix=lms-"

SRCREV = "454be49d1fd22c82d78bddd91f61e478c50b8aa0"
SRC_URI = "git://github.com/profusion/lightmediascanner.git \
	   file://0001-Install-binaries-in-bin-directory.patch"

S = "${WORKDIR}/git"

FILES_${PN} += "${libdir}/lightmediascanner/plugins/*.so"
