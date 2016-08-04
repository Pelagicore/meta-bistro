#
#   Copyright (C) 2015 Pelagicore AB
#
DEPENDS := "${@oe_filter_out('gconf', '${DEPENDS}', d)}"
EXTRA_OECONF += " --disable-gconf "

RDEPENDS_pulseaudio-server += " \
    pulseaudio-misc \
"

SRC_URI += "file://pulseaudio-mutex-assert-fix.patch"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
