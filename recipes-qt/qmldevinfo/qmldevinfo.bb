#
#   Copyright (C) 2014-2015 Pelagicore AB
#
inherit qmake5

# Disable parallel make until .pro files properly set dependencies
PARALLEL_MAKE = "-j1"
OE_QMAKE_PATH_HEADERS = "${OE_QMAKE_PATH_QT_HEADERS}"

DEPENDS += "qtbase qtdeclarative qtquick1"

SRC_URI = "git://git@github.com/Pelagicore/qmldevinfo.git;branch=master;protocol=ssh"
SRCREV = "50a305aa42a8e542cac66b843fdbfaff08d58bf0"

LICENSE = "MPL-2.0"

PV = "1.0+git${SRCREV}"
PR = "r1"

S = "${WORKDIR}/git/"
B = "${WORKDIR}/build/"

FILES_${PN} += " \
    /usr/lib/qt5/qml/com/pelagicore/qmldevinfo/* \
"

FILES_${PN}-dbg += " \
    /usr/lib/qt5/qml/com/pelagicore/qmldevinfo/.debug \
"

PACKAGES = "${PN}-dbg ${PN}"
