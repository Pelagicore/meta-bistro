#
#   Copyright (C) 2015 Pelagicore AB
#
DESCRIPTION = "Tracker is a tool designed to extract information and metadata about your personal data so that it can be searched easily and quickly."
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=ee31012bf90e7b8c108c69f197f3e3a4"
HOMEPAGE = "http://projects.gnome.org/tracker/"

# Don't use this recipe per default
DEFAULT_PREFERENCE = "-1"

inherit autotools pkgconfig gettext gsettings

DEPENDS = "file gstreamer1.0 gstreamer1.0-plugins-base gamin dbus libexif gettext sqlite3 icu ossp-uuid libpng gdk-pixbuf gtk-doc vala-native giflib tiff flac"
RDEPENDS_${PN} = "dconf shared-mime-info eglibc-gconv-cp1252"

PR = "r0"

VER_DIR = "0.18"
SRCREV = "051076d4190c9596383b41e9b391d2d88332e4d4"
SRC_URI = "git://github.com/Pelagicore/tracker-ivi.git;protocol=https;branch=master \
	   file://sqlite-threadsafe-check-disable.patch \
	   file://sqlite-FTS-check-disable.patch"
S = "${WORKDIR}/git"

EXTRA_OECONF += " tracker_cv_have_ioprio=yes		\
	--disable-introspection				\
	--disable-unit-tests				\
	--disable-upower				\
	--disable-hal					\
	--disable-gnome-keyring				\
	--disable-libsecret				\
	--disable-network-manager			\
	--disable-libiptcdata				\
	--disable-miner-rss				\
	--disable-miner-evolution			\
	--disable-miner-thunderbird			\
	--disable-miner-firefox				\
	--disable-nautilus-extension			\
	--disable-taglib				\
	--disable-tracker-needle			\
	--disable-tracker-preferences			\
	--disable-libxml2				\
	--disable-poppler				\
	--disable-libgxps				\
	--disable-libgsf				\
	--disable-libosinfo				\
	--disable-libcue				\
	--disable-playlist				\
	--disable-exempi				\
	--disable-abiword				\
	--disable-dvi					\
	--disable-ps					\
	--disable-text					\
	--disable-icon					\
	--disable-artwork				\
							\
	--enable-libexif				\
	--enable-gdkpixbuf				\
	--enable-generic-media-extractor=gstreamer	\
	--enable-libgif					\
	--enable-libjpeg				\
	--enable-libtiff				\
	--enable-libvorbis				\
	--enable-libflac				\
	--enable-png-faster				\
							\
	--with-unicode-support=libicu"

autotools_preconfigure() {
  gtkdocize
}

do_install_append() {
  cp -PpR ${D}${STAGING_DATADIR}/* ${D}${datadir}/ || true
  install -d ${D}/${sysconfdir}/X11/Xsession.d/
}

FILES_${PN} += "${datadir}/dbus-1/ \
                ${libdir}/tracker-${VER_DIR}/*.so.* \
                ${libdir}/tracker-${VER_DIR}/extract-modules/*.so \
                ${datadir}/glib-2.0/schemas/*.xml \
                ${datadir}/icons/hicolor/*/apps/tracker.* \
		${prefix}/share/tracker/extract-rules/*.rule \
		${prefix}/share/tracker/ontologies/*.description \
		${prefix}/share/tracker/ontologies/*.ontology"

SRC_URI[md5sum] = "f3a871beeebf86fd752863ebd22af9ac"
SRC_URI[sha256sum] = "9b59330aa2e9e09feee587ded895e9247f71fc25f46b023d616d9969314bc7f1"
