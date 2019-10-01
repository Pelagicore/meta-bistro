require recipes-qt/qt5/qt5.inc

# FIXME!!!
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/LGPL-2.1;md5=1a6d268fd218675ffea8be556788b780"

DEPENDS = "qtbase qtdeclarative"

INC_PR = "r0"

FILES_${PN} += "${libdir}/*.so.*"
FILES_${PN}-qmlplugins = " \
      ${libdir}/qt5/qml/QtOrganizer/* \
       ${libdir}/qt5/qml/QtContacts/* \
       "
FILES_${PN}-qmlplugins-dbg += " \
       ${libdir}/qt5/qml/QtOrganizer/.debug/* \
       ${libdir}/qt5/qml/QtContacts/.debug/* \
       "
FILES_${PN}-plugins += "${libdir}/qt5/plugins/*/*.so"
FILES_${PN}-plugins-dbg = "${libdir}/qt5/plugins/*/.debug/*"

