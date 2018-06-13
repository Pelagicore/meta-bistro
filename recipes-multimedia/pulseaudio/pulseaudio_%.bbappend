#
#   Copyright (C) 2015 Pelagicore AB
#   Copyright (C) 2018 Luxoft Sweden AB
#
#   SPDX-License-Identifier: MIT
#
DEPENDS := "${@oe.utils.str_filter_out('gconf', '${DEPENDS}', d)}"
EXTRA_OECONF += " --disable-gconf "

RDEPENDS_pulseaudio-server += " \
    pulseaudio-misc \
"

SRC_URI += "file://pulseaudio-mutex-assert-fix.patch"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
