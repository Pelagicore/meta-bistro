#
#   Copyright (C) 2015 Pelagicore AB
#
DESCRIPTION = "common-api-c++-native"
HOMEPAGE = "http://www.pelagicore.com"
LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=815ca599c9df247a0c7f619bab123dad"
PR = "r0"

DEPENDS = "common-api-cmdline-codegen common-api-c++-codegen"

inherit cmake maven-properties

GIT_REPO = "git://github.com/Pelagicore/common-api-dbus-tools.git;protocol=https"
S = "${WORKDIR}/git"

#EXTRA_OECMAKE += " -DMAVEN_OPTIONS=${MAVEN_OPTIONS}"

SRC_URI = "${GIT_REPO};branch=master"
SRCREV = "432972cd710368e69f0b96954bc8388a6033d984"
PV = "3.1.5.${SRCREV}"

FILES_${PN} += "${datadir}/* \
${libdir}/cmake/* \
"

BBCLASSEXTEND = "native nativesdk"
