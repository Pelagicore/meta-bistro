#
#   Copyright (C) 2015 Pelagicore AB
#   All rights reserved.
#
DESCRIPTION = "configuration database system"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=2d5025d4aa3495befef8f17206a5b0a1"

SRC_URI = "${GNOME_MIRROR}/${PN}/0.11/${BPN}-${PV}.tar.xz"
SRC_URI[md5sum] = "9ca9af551d83c81fad13538340c484c1"
SRC_URI[sha256sum] = "4625978257cd8c273a4d31ea7a8788326df63267fc0236b6d879e891cd48fad9"


# not sure which of those is actually needed ... most likely just autotools and pkgconfig?
inherit autotools pkgconfig gettext gsettings

DEPENDS = "glib-2.0 dbus"


EXTRA_OECONF += "--disable-editor"

FILES_${PN} += "${datadir}/dbus-1/ \
                ${libdir}/gio/modules/*.so"
FILES_${PN}-dbg += "${libdir}/gio/modules/.debug/*.so"

#pkg_postinst_${PN} () {
#if [ -n "$D" ]; then
#    exit 1
#fi

#glib-compile-schemas ${datadir}/glib-2.0/schemas
#}
