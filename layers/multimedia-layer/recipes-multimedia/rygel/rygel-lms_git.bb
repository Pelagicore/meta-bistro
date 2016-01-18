LICENSE = "GPLv2"

inherit gnome vala cmake

SRC_URI = "git://github.com/Pelagicore/Media-Manager-Rygel-LMS-Plugin.git;protocol=https;branch=rygel24 \
           file://fix-typecast.patch \
           "

SRCREV = "5f0735d585155658fdf849dd893e0ec21bf890b5"
PV = "0.23.3+mm-git${SRCPV}"
S = "${WORKDIR}/git/"

DESCRIPTION = "LMS plugin for Rygel"
SECTION = "network/multimedia"
DEPENDS = "rygel lightmediascanner"

FILES_${PN} += "${libdir}/rygel-2.4/plugins"
FILES_${PN}-dbg += "${libdir}/rygel-2.4/plugins/.debug"
LIC_FILES_CHKSUM = "file://${WORKDIR}/git/LICENSE;md5=b234ee4d69f5fce4486a80fdaf4a4263"

