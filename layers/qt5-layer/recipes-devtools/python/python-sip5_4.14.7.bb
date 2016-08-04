#
#   Copyright (C) 2015 Pelagicore AB
#
DESCRIPTION = "Runtime helper for sip-generated python wrapper libraries"
SECTION = "devel/python"
HOMEPAGE = "http://www.riverbankcomputing.co.uk/sip"
AUTHOR = "Phil Thompson"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://siplib.sbf.in;endline=15;md5=1974f77bf62e3661ef0a9f58ce0068d9"
DEPENDS = "python qtbase"
RDEPENDS_${PN} = "python-core"

PR = "r1"

SRC_URI = "http://sourceforge.net/projects/pyqt/files/sip/sip-4.14.7/sip-4.14.7.tar.gz"
SRC_URI[md5sum] = "f5c8001f16ecffd711708de0e07d542f"
SRC_URI[sha256sum] = "05669120a53a2c98f98560424cda413ae95f5528494c80764004dbea78d361b7"

S = "${WORKDIR}/sip-4.14.7/siplib"

inherit distutils-base qmake5
OE_QMAKE_PATH_HEADERS = "${OE_QMAKE_PATH_QT_HEADERS}"

EXTRA_QMAKEVARS_POST += " TEMPLATE=lib \
                         CONFIG=console \
                         DESTDIR= \
                         VERSION=1.0.0 \
                         TARGET=sip \
                         DEFINES=SIP_QT_SUPPORT \
                         INCLUDEPATH+=. \
                         INCLUDEPATH+=${STAGING_INCDIR}/${PYTHON_DIR} \
                         INCLUDEPATH+=${STAGING_INCDIR} \
                         CONFIG+=link_pkgconfig \
                         PKGCONFIG+=python "


do_configure_prepend() {
    cat ${S}/siplib.sbf.in | sed s,target,TARGET, | sed s,sources,SOURCES, | sed s,headers,HEADERS, | sed s,@CFG_MODULE_BASENAME@,sip, > ${S}/siplib.pro
    cat ${S}/siplib.c.in | sed s,@CFG_MODULE_BASENAME@,sip, > ${S}/siplib.c
    cat ${S}/sip.h.in | sed -e s,@CFG_MODULE_NAME@,sip,g > ${S}/sip.h
}

do_install() {
    install -d ${D}${libdir}/${PYTHON_DIR}/site-packages/
    install -m 0755 libsip.so.1.0.0 ${D}${libdir}/${PYTHON_DIR}/site-packages/sip.so
    # sipconfig.py sipdistutils.py
    install -d ${D}${includedir}
    install -m 0644 ${S}/../siplib/sip.h ${D}${includedir}/sip.h
}

FILES_${PN} = "${libdir}/${PYTHON_DIR}/site-packages/sip.so"

