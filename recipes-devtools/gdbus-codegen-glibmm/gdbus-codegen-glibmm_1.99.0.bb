SUMMARY = "This is a code generator for generating D-Bus stubs and proxies from XML introspection files"
DESCRIPTION = "This is a code generator for generating D-Bus stubs and proxies \
from XML introspection files. The generated stubs and proxies are implemented \
using glibmm and giomm. This generator is based on the gdbus-codegen code \
generator which ships with glib."
HOMEPAGE = "https://github.com/Pelagicore/gdbus-codegen-glibmm"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://LICENSE;md5=4fbd65380cdd255951079008b364516c"
DEPENDS = "python3-jinja2-native python3-markupsafe-native"
SRCREV = "5325d4fc92924102a8427fb6964a19b3a0ca2a7c"

SRC_URI = "git://github.com/pelagicore/gdbus-codegen-glibmm;branch=master"

S = "${WORKDIR}/git/"

inherit setuptools3

do_install_append_class-native() {
    sed -i '1 c\#!/usr/bin/env nativepython3' ${D}${bindir}/${BPN}2
}

# Dependencies for building this software.
RDEPENDS_${PN} = "python3 python3-setuptools python3-jinja2"

BBCLASSEXTEND = "native nativesdk"

PACKAGE =+ "${PN}"

