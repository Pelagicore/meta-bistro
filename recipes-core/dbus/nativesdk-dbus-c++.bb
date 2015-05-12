#
#   Copyright (C) 2015 Pelagicore AB
#   All rights reserved.
#
inherit nativesdk

DESCRIPTION = "C++ bindings for dbus"
LICENSE = "LGPLv2.1+"
SECTION = "libs"
DEPENDS = "dbus expat glib-2.0"

SRCREV = "1f6f3e6e966e0b453edc4a82338dc27966c37505"

PE = "1"
PV = "0.6.0-pre1+gitr${SRCPV}"

SRC_URI = " \
	git://gitorious.org/dbus-cplusplus/mainline.git;protocol=git \
	file://dbus-c++.patch \
	"

S = "${WORKDIR}/git"

inherit autotools pkgconfig

EXTRA_OECONF += "--disable-ecore --disable-tests"

# Don't build any examples, tests or documentation, and we build for what yocto wants.
do_configure_append () {
    sed -i 's#SUBDIRS = src tools data doc examples  test#SUBDIRS = src tools data#g' ${S}/Makefile.am
    sed -i 's#CXX = $(CXX_FOR_BUILD)##g' ${S}/tools/Makefile.am
}

FILES_${PN} += "${bindir}/dbusxx*"

LIC_FILES_CHKSUM = "file://COPYING;md5=fbc093901857fcd118f065f900982c24"

