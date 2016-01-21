#
#   Copyright (C) 2015 Pelagicore AB
#   All rights reserved.
#
DESCRIPTION = "Qtify"
HOMEPAGE = "https://gitorious.org/qtify"
LICENSE = "CLOSED"
PR = "r0"

DEPENDS = "qtbase qtmultimedia libspotify qtquickcontrols"
RDEPENDS_${PN} = "iproute2"

SRC_URI = "git://git@gitorious.org/qtify/qtify.git;protocol=http;branch=master"
SRCREV = "3bd37aeb61b8049682e58b8501d8f1c18290e093"

inherit qmake5_paths qmake5

OE_QMAKE_PATH_HEADERS = "${OE_QMAKE_PATH_QT_HEADERS}"

PV = "0.1+git${SRCREV}"

S = "${WORKDIR}/git/"

do_install() {
       install -D ${WORKDIR}/build/Qtify ${D}/${bindir}/Qtify
}
