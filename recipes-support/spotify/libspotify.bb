#
#   Copyright (C) 2015 Pelagicore AB

DESCRIPTION = "libspotify"
HOMEPAGE = "http://developer.spotify.com"
LICENSE = "commercial"
PR = "r0"

# TODO: Update with proper COPYING file

LICENSE = "commercial"
LIC_FILES_CHKSUM = "file://LICENSE;md5=6a7efe9489ebf22e7f43756283669839"

SRC_URI_arm = " \
	http://developer.spotify.com/download/libspotify/libspotify-12.1.51-Linux-armv7-release.tar.gz \
	"

S_arm = "${WORKDIR}/libspotify-12.1.51-Linux-armv7-release/"

PACKAGES =+ " \
	${PN}-test \
	${PN}-test-dev \
	${PN}-test-dbg \
	${PN}-qmlplugin-dbg \
	${PN}-qmlplugin \
	"
do_configure() {
}

do_compile() {
}

do_install() {
	mkdir -p ${D}/${libdir}/pkgconfig
	mkdir -p ${D}/${includedir}/libspotify
	install include/libspotify/api.h ${D}/${includedir}/libspotify
	install -T lib/libspotify.so.12 ${D}/${libdir}/libspotify.so.12.1.51
	ln -sf libspotify.so.12.1.51 ${D}/${libdir}/libspotify.so.12
	ln -sf libspotify.so.12.1.51 ${D}/${libdir}/libspotify.so

	install lib/pkgconfig/libspotify.pc ${D}/${libdir}/pkgconfig
	sed -i 's:PKG_PREFIX:/usr:g' ${D}/${libdir}/pkgconfig/libspotify.pc
}

INSANE_SKIP_${PN} = "ldflags"

SRC_URI[md5sum] = "eb7e98849b3bb6d364fa74034602afbf"
SRC_URI[sha256sum] = "ad27b6c5aee5382b66b39bfea3b1752076b7abcc445979ce25c1ec9d7ff3aeda"
