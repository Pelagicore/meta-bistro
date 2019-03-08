SUMMARY = "Alexa Auto SDK"
HOMEPAGE = "https://developer.amazon.com/alexa-voice-service/alexa-auto-sdk"
SECTION = "libs"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRC_URI = "https://github.com/alexa/aac-sdk/archive/v${PV}.tar.gz"
SRC_URI[md5sum] = "0426c28b658649b6e75e2b73490483c2"
SRC_URI[sha256sum] = "6ffb1c301a2fb93b3e3309ef80852b5f230ada98463e552fca57c6f106d81519"

DEPENDS = "avs-device-sdk gtest"
RDEPENDS_${PN} = "avs-device-sdk"

inherit cmake pkgconfig

S = "${WORKDIR}/aac-sdk-${PV}"

FILES_SOLIBSDEV = ""
SOLIBS = ".so"
