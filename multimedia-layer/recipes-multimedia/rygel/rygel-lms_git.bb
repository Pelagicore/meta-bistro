#
#   Copyright (C) 2015 Pelagicore AB
#   All rights reserved.
#
LICENSE = "GPLv2"

inherit gnome vala cmake

SRC_URI = " \
	git://github.com/Pelagicore/Media-Manager-Rygel-LMS-Plugin.git;protocol=https;branch=rygel24 \
	"

SRCREV = "fd0213bf3f55e4b3fef35024a511325b429a8afd"
PV = "0.23.3+mm-git${SRCPV}"
S = "${WORKDIR}/git/"

DESCRIPTION = "LMS plugin for Rygel"
SECTION = "network/multimedia"
DEPENDS = "rygel lms"

FILES_${PN} += "${libdir}/rygel-2.4/plugins"
FILES_${PN}-dbg += "${libdir}/rygel-2.4/plugins/.debug"
LIC_FILES_CHKSUM = "file://${WORKDIR}/git/LICENSE;md5=b234ee4d69f5fce4486a80fdaf4a4263"

