#
#   Copyright (C) 2015 Pelagicore AB
#   All rights reserved.
#
DESCRIPTION = "MediaArt Library"
SRC_URI = " \
	http://ftp.gnome.org/pub/GNOME/sources/libmediaart/0.6/libmediaart-0.6.0.tar.xz \
	file://libmediaart-disable-gobject-introspection.patch \
	file://libmediaart-1.0.vapi \
"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://${WORKDIR}/${PN}-${PV}/COPYING.LESSER;md5=4fbd65380cdd255951079008b364516c"

inherit autotools gettext
DEPENDS += "glib-2.0 gdk-pixbuf"

EXTRA_OECONF = "--enable-introspection=no --enable-qt=no"
SRC_URI[md5sum] = "8bd508886c47397925771e2717c80d52"
SRC_URI[sha256sum] = "bb70add2054ef26fc5bcdf2e96b0b25bd5a89c5601aac7f0e40df92a10d404ab"

do_install_append() {
	install -Dm0644 ${WORKDIR}/libmediaart-1.0.vapi ${D}/${datadir}/vala-0.22/vapi/libmediaart-1.0.vapi
}
