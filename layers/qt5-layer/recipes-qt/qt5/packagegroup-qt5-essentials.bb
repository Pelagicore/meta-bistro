#
#   Copyright (C) 2015 Pelagicore AB
#
DESCRIPTION = "All Qt5 Essentials modules"
LICENSE = "MIT"

inherit packagegroup

RDEPENDS_${PN} += " \
         qtbase \
         qtbase-plugins \
         qtbase-fonts \
         qtdeclarative \
         qtdeclarative-plugins \
         qtdeclarative-qmlplugins \
         qtdeclarative-tools \
         qtmultimedia \
         qtmultimedia-plugins \
         qtmultimedia-qmlplugins \
         qtwebkit \
         qtwebkit-qmlplugins \
         qtsvg \
         qtsvg-plugins \
         "
