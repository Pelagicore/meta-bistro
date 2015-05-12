#
#   Copyright (C) 2015 Pelagicore AB
#   All rights reserved.
#
DESCRIPTION = "Python Qt5 Bindings"
HOMEPAGE = "http://riverbankcomputing.co.uk"
AUTHOR = "Phil Thomson @ riverbank.co.uk"
SECTION = "devel/python"
LICENSE = "GPLv2 & GPLv3 & GPL_EXCEPTION"
LIC_FILES_CHKSUM = "file://LICENSE;md5=40f9996e677b16c4cc0bc1886c8af247"

DEPENDS = "sip-native5 python-sip5"
RDEPENDS_${PN} = "python-core"

PYQT_OE_VERSION = "Qt_5_0_1"
PR = "r5"

SRC_URI = "http://sourceforge.net/projects/pyqt/files/PyQt5/PyQt-5.0/PyQt-gpl-5.0.tar.gz"
SRC_URI[md5sum] = "771b3d680a905ab5b6a13fa8707dc70a"
SRC_URI[sha256sum] = "1357216021e16f147d76d7ad618756cbdc7fc45e5a13de3cc540920b31da693d"
SRC_URI += "file://pyqt-pkgconfig-python.patch"
S = "${WORKDIR}/PyQt-gpl-5.0/"

inherit sip5 distutils-base qmake5
OE_QMAKE_PATH_HEADERS = "${OE_QMAKE_PATH_QT_HEADERS}"
B = "${S}"

PARALLEL_MAKE = ""

QMAKE_PROFILES = "pyqt.pro"
# NOTE: has to match with MIN(qt version we have in OE, last known Qt version by SIP/PyQt)
EXTRA_SIPTAGS = "-tWS_X11 -t${PYQT_OE_VERSION} -xVendorID -xPyQt_SessionManager -xPyQt_Accessibility"
###EXTRA_OEMAKE = " MAKEFLAGS= "

# arm and mips need extra params for the qreal issue
EXTRA_SIPTAGS_append_arm = " -x PyQt_qreal_double"
EXTRA_SIPTAGS_append_mipsel = " -x PyQt_qreal_double" 

SIP_MODULES = "QtCore QtDBus"
MAKE_MODULES = "qpy ${SIP_MODULES}"

EXTRA_QMAKEVARS_POST += "\
    INCLUDEPATH+=${OE_QMAKE_PATH_HEADERS}/Qt \
    INCLUDEPATH+=${STAGING_INCDIR}/${PYTHON_DIR} \
    INCLUDEPATH+=../qpy/QtCore \
    INCLUDEPATH+=../qpy/QtGui \
    INCLUDEPATH+=../qpy/QtDBus \
    INCLUDEPATH+=../qpy/QtOpenGL \
    INCLUDEPATH+=${STAGING_INCDIR}/${QT_DIR_NAME}/QtCore \
    INCLUDEPATH+=${STAGING_INCDIR}/${QT_DIR_NAME}/QtGui \
    INCLUDEPATH+=${STAGING_INCDIR}/${QT_DIR_NAME}/QtDBus \
    INCLUDEPATH+=${STAGING_INCDIR}/${QT_DIR_NAME}/QtWebKit \
    INCLUDEPATH+=${STAGING_INCDIR}/${QT_DIR_NAME}/QtNetwork \
    INCLUDEPATH+=${STAGING_INCDIR}/${QT_DIR_NAME}/QtOpenGL \
"
FIX_QREAL = "\
"

do_generate_prepend() {
    for i in ${FIX_QREAL}; do
        sed -i -e s,qreal,float,g sip/$i
    done
}

do_configure_prepend() {
    printf "TEMPLATE=subdirs\nSUBDIRS=${MAKE_MODULES}\n" >pyqt.pro
    printf "TEMPLATE=subdirs\nSUBDIRS=QtCore QtDBus\n" >qpy/qpy.pro
    ln -sf ./qpycore.pro qpy/QtCore/QtCore.pro
    ln -sf ./qpydbus.pro qpy/QtDBus/QtDBus.pro
#    ln -sf ./qpygui.pro qpy/QtGui/QtGui.pro
#    ln -sf ./qpopengl.pro qpy/QtOpenGL/QtOpenGL.pro
    echo "INCLUDEPATH+=${S}/QtCore" >>qpy/QtCore/QtCore.pro
    echo "INCLUDEPATH+=${S}/QtDBus" >>qpy/QtDBus/QtDBus.pro
#    echo "INCLUDEPATH+=${S}/QtGui" >>qpy/QtCore/QtGui.pro
#    echo "INCLUDEPATH+=${S}/QtOpenGL" >>qpy/QtCore/QtOpenGL.pro
    echo "LIBS+=-L../qpy/QtCore/ -lqpycore" >>QtCore/QtCore.pro
    echo "CONFIG += link_pkgconfig" >>QtCore/QtCore.pro
    echo "PKGCONFIG += python" >>QtCore/QtCore.pro
#    echo "LIBS+=-L../qpy/QtDBus/ -lqpydbus" >>QtDBus/QtDBus.pro
#    echo "LIBS+=-L../qpy/QtGui/ -lqpygui" >>QtGui/QtGui.pro
#    echo "LIBS+=-L../qpy/QtOpenGL/ -lqpyopengl" >>QtOpenGL/QtOpenGL.pro
    # hack for broken generated code (duplicated sipCpp declaration).
    patch -p1 < ${WORKDIR}/pyqt-generated.patch || echo "pyqt-generated.patch failed to apply, probably reexecuting do_configure, ignoring that"
    sed 's#@@PYQT_SIP_FLAGS@@#${EXTRA_SIPTAGS}#g' qpy/QtCore/qpycore_post_init.cpp.in > qpy/QtCore/qpycore_post_init.cpp 
}

do_install() {
    install -d ${D}${libdir}/${PYTHON_DIR}/site-packages/PyQt5
    install -d ${D}${datadir}/sip/qt/
    for module in ${SIP_MODULES}
    do
        install -m 0644 ${S}/sip/${module}/*.sip ${D}${datadir}/sip/qt/
        echo "from PyQt5.${module} import *\n" >> ${D}${libdir}/${PYTHON_DIR}/site-packages/PyQt5/Qt.py
        install -m 0755 ${module}/lib${module}.so ${D}${libdir}/${PYTHON_DIR}/site-packages/PyQt5/${module}.so
    done
    cp __init__.py ${D}${libdir}/${PYTHON_DIR}/site-packages/PyQt5/
}

FILES_${PN} = "${libdir}/${PYTHON_DIR}/site-packages ${datadir}/sip/qt/"
