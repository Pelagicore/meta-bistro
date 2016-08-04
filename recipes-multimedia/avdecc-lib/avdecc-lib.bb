#
#   Copyright (C) 2015 Pelagicore AB
#
DESCRIPTION = "avdecc-lib"
HOMEPAGE = "https://github.com/audioscience/avdecc-lib"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=89e9087cf8523e423297c8e41541ac39"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

inherit cmake

EXTRA_OECMAKE += "-DCMAKE_CXX_FLAGS='${TARGET_CC_ARCH}'"

SRC_URI = " \
    git://github.com/audioscience/avdecc-lib.git;branch=master;protocol=https \
    file://avdecc-lib-fix-cmake-variables.patch \
    file://clone_using_https.patch \
    "

SRCREV = "f3fa82399fb8a5664ab2f4f2c1430b3a28e3fc2b"

S = "${WORKDIR}/git/"

do_submodule_initupdate() {
    cd ${S}
    git submodule init
    git submodule update
}

do_install() {
    mkdir -p ${D}/${bindir}
    mkdir -p ${D}/${libdir}

    install ${B}/controller/lib/libcontroller.so ${D}/${libdir}
    install ${B}/controller/app/cmdline/avdecccmdline ${D}/${bindir}

}

FILES_${PN} =+ " \
    ${bindir}/* \
    ${libdir}/* \
"

addtask submodule_initupdate after do_patch before do_configure
