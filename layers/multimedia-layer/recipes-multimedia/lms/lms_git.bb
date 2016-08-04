#
#   Copyright (C) 2015 Pelagicore AB
#
DESCRIPTION = "Light Media Scanner"
SECTION = "multimedia"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://${S}/COPYING;md5=a6f89e2100d9b6cdffcea4f398e37343"

DEPENDS += "glib-2.0 sqlite3"
RDEPENDS_${PN} = "buswatcher"

SRC_URI = " \
    git://github.com/Pelagicore/Media-Manager-lightmediascanner-patched;protocol=https \
"

SRCREV = "6aa2d10e80976c815fb40d7e5aacbf104950923d"
PV = "git${SRCPV}"
S = "${WORKDIR}/git/"

inherit autotools gettext systemd

EXTRA_OECONF = "--disable-generic"

FILES_${PN} += " \
	${libdir}/lightmediascanner/plugins/*.so \
"

FILES_${PN}-dev += "${libdir}/lightmediascanner/plugins/*.la"
FILES_${PN}-staticdev += "${libdir}/lightmediascanner/plugins/*.a"
FILES_${PN}-dbg += "${libdir}/lightmediascanner/plugins/.debug"
