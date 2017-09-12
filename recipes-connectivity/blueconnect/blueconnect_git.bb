#
#   Copyright (C) 2017 Pelagicore AB
#   SPDX-License-Identifier: MIT
#

SUMMARY = "Library for building bluetooth demos in Qt"
DESCRIPTION = "BlueConnect is a library that is used to create simple bluetooth demos in Qt."
AUTHOR = "Viktor Sjölind <viktor.sjolind@pelagicore.com>"
HOMEPAGE = "https://github.com/Pelagicore/BlueConnect"
LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=815ca599c9df247a0c7f619bab123dad"

DEPENDS = "qtbase qtdeclarative"
RDEPENDS_${PN} = " \
    qtbase \
    qtdeclarative \
    qtquickcontrols-qmlplugins \
    bluez5 \
    "

RRECOMMENDS = " ofono "

PV = "0.1+git${SRCREV}"
PR = "r0"

SRC_URI = "git://github.com/pelagicore/blueconnect;branch=master"
SRCREV = "a14c30d5a45a7864ed942386801bb070b6128dd0"

S = "${WORKDIR}/git"

inherit qmake5

FILES_${PN} += "${libdir}/qt5/qml/com/pelagicore/bluetooth/*"
