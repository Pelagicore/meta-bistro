#
#   Copyright (C) 2015 Pelagicore AB
#
DESCRIPTION = "SIP is a C++/Python Wrapper Generator"
AUTHOR = "Phil Thompson"
HOMEPAGE = "http://www.riverbankcomputing.co.uk/sip"
SECTION = "devel"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://sipgen.sbf;endline=15;md5=b604c7a1f8518b7d563e19ee891e65e6"

PR = "r1"

SRC_URI = "http://sourceforge.net/projects/pyqt/files/sip/sip-4.14.7/sip-4.14.7.tar.gz"
SRC_URI[md5sum] = "f5c8001f16ecffd711708de0e07d542f"
SRC_URI[sha256sum] = "05669120a53a2c98f98560424cda413ae95f5528494c80764004dbea78d361b7"
S = "${WORKDIR}/sip-4.14.7/sipgen"

inherit qmake5 native python-dir

EXTRA_QMAKEVARS_POST += "DESTDIR=${S} CONFIG=console"

export BUILD_SYS
export HOST_SYS
export STAGING_LIBDIR
export STAGING_INCDIR

do_configure_prepend() {
    cat ${S}/sipgen.sbf | sed s,target,TARGET, | sed s,sources,SOURCES, | sed s,headers,HEADERS, > ${S}/sipgen.pro
}
do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${S}/sip ${D}${bindir}/sip
    # python-pyqt expects sip4
    ln -sf ${S}/sip ${D}${bindir}/sip5
    cd ${WORKDIR}/sip-4.14.7 && python configure.py
    install -d ${D}${PYTHON_SITEPACKAGES_DIR}
    install -m 0755 sip*.py ${D}${PYTHON_SITEPACKAGES_DIR}
}
