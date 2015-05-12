#
#   Copyright (C) 2015 Pelagicore AB
#   All rights reserved.
#
DESCRIPTION = "Tracker is a tool designed to extract information and metadata about your personal data so that it can be searched easily and quickly."
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=ee31012bf90e7b8c108c69f197f3e3a4"
HOMEPAGE = "http://projects.gnome.org/tracker/"

inherit autotools pkgconfig gettext gsettings

DEPENDS = "file gstreamer gamin dbus libexif gettext sqlite3 icu ossp-uuid libpng gdk-pixbuf"
RDEPENDS_${PN} = "dconf shared-mime-info"

PR = "r7"


VER_DIR = "0.14"
SRC_URI = "http://ftp.gnome.org/pub/GNOME/sources/tracker/${VER_DIR}/tracker-${PV}.tar.xz \
            file://0005-Fix-missing-gobject-introspection-checks.patch \
            file://enable-sqlite-crosscompile.patch \
            file://fix-removable-media-detection.patch \
            file://90tracker"

EXTRA_OECONF += " tracker_cv_have_ioprio=yes --disable-introspection"

# Disable the desktop-centric miners
EXTRA_OECONF += "--disable-miner-thunderbird --disable-miner-firefox \
                 --disable-miner-evolution --disable-miner-flickr"

LEAD_SONAME = "libtrackerclient.so.0"

do_install_append() {
   cp -PpR ${D}${STAGING_DATADIR}/* ${D}${datadir}/ || true
#  install -d ${D}/${sysconfdir}/X11/Xsession.d/
#  install -m 0755 ${WORKDIR}/90tracker  ${D}/${sysconfdir}/X11/Xsession.d/
}

PACKAGES =+ "${PN}-extract-module-mp3 ${PN}-tests ${PN}-vala"

FILES_${PN} += "${datadir}/dbus-1/ \
                ${libdir}/tracker-${VER_DIR}/*.so.* \
                ${libdir}/tracker-${VER_DIR}/extract-modules/*.so \
                ${datadir}/glib-2.0/schemas/*.xml \
                ${datadir}/icons/hicolor/*/apps/tracker.*"

FILES_${PN}-dev += "${libdir}/tracker-${VER_DIR}/*.la \
                    ${libdir}/tracker-${VER_DIR}/*.so \
                    ${libdir}/tracker-${VER_DIR}/extract-modules/*.la"
FILES_${PN}-dbg += "${libdir}/*/*/.debug \
                    ${libdir}/*/.debug \
                    ${libdir}/.debug \
                    ${bindir}/.debug \
                    ${srcdir}"
FILES_${PN}-tests = "${datadir}/tracker-tests/"
FILES_${PN}-vala = "${datadir}/vala"
FILES_${PN}-extract-module-mp3 = "${libdir}/tracker-${VER_DIR}/extract-modules/libextract-mp3.so"

SRC_URI[md5sum] = "f3a871beeebf86fd752863ebd22af9ac"
SRC_URI[sha256sum] = "9b59330aa2e9e09feee587ded895e9247f71fc25f46b023d616d9969314bc7f1"
