#
#   Copyright (C) 2015 Pelagicore AB
#   All rights reserved.
#
DESCRIPTION = "C++ bindings for dbus"
LICENSE = "LGPLv2.1+"
SECTION = "libs"
DEPENDS = "dbus expat expat-native dbus-c++-native glib-2.0"

# When we are building the -native version the dep to the native version should be removed
DEPENDS_remove_class-native = " dbus-c++-native "

SRCREV = "1f6f3e6e966e0b453edc4a82338dc27966c37505"

PE = "1"
PV = "0.6.0-pre1+gitr${SRCPV}"

SRC_URI = " \
	git://gitorious.org/dbus-cplusplus/mainline.git;protocol=https \
	file://dbus-c++.patch \
	"

S = "${WORKDIR}/git"

inherit autotools pkgconfig
EXTRA_OECONF += "--disable-ecore --disable-tests --with-build-libdbus-cxx=${STAGING_LIBDIR_NATIVE}"

# Fix some build issues
do_configure_append () {
	sed -i 's#dbusxx_xml2cpp_LDADD = $(xml_LIBS)#dbusxx_xml2cpp_LDADD = $(xml_LIBS) -L${STAGING_LIBDIR_NATIVE}#g' ${S}/tools/Makefile.am
	sed -i 's#libdbus_cxx_la = $(BUILD_LIBDBUS_CXX_DIR)/src/#libdbus_cxx_la = $(BUILD_LIBDBUS_CXX_DIR)/#g' ${S}/tools/Makefile.am
	sed -i 's#-I$(top_builddir)/include#-I$(top_builddir)/include -I${STAGING_INCDIR_NATIVE}#g' ${S}/tools/Makefile.am
}

# Remove files built for host arch
do_install_append () {
	rm -rf ${D}/usr/bin/dbusxx-*
}

FILES_${PN} = "${libdir}/*.so.*"

# We need a -native version of this package so that the tools can be built for host
BBCLASSEXTEND = "native"

LIC_FILES_CHKSUM = "file://COPYING;md5=fbc093901857fcd118f065f900982c24"
