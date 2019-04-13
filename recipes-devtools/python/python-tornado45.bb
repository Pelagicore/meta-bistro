#
#   Copyright (C) 2018 Koen Kooi
#
#   SPDX-License-Identifier: MIT
#

SUMMARY  = "Tornado is an open source version of the scalable, non-blocking web server and tools that power FriendFeed."
DESCRIPTION = "Tornado is a Python web framework and asynchronous networking library, originally developed at FriendFeed. \
By using non-blocking network I/O, Tornado can scale to tens of thousands of open connections, making it ideal for long \
polling, WebSockets, and other applications that require a long-lived connection to each user."
HOMEPAGE = "http://www.tornadoweb.org/en/stable/"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

PV = "4.5.3"

SRCREV = "8e9e75502ff910629663c4cdd7779d43ea2dd150"
SRC_URI = "git://github.com/tornadoweb/tornado.git;branch=branch4.5"

S = "${WORKDIR}/git"

inherit setuptools

RDEPENDS_${PN} += " \
    ${PYTHON_PN}-backports-abc \
    ${PYTHON_PN}-backports-ssl \
    ${PYTHON_PN}-singledispatch \
    ${PYTHON_PN}-subprocess \
"

RDEPENDS_${PN} += "${PYTHON_PN}-compression ${PYTHON_PN}-numbers ${PYTHON_PN}-email \
                   ${PYTHON_PN}-pkgutil ${PYTHON_PN}-html ${PYTHON_PN}-json ${PYTHON_PN}-certifi ${PYTHON_PN}-threading \
                   ${PYTHON_PN}-ctypes"

RDEPENDS_${PN}-test += "${PN} ${PYTHON_PN}-unittest"

PACKAGES =+ "\
    ${PN}-test \
"

FILES_${PN}-test = " \
    ${libdir}/${PYTHON_DIR}/site-packages/*/test \
    ${libdir}/${PYTHON_DIR}/site-packages/*/testing.py* \
"

RCONFLICTS_${PN} = "python-tornado"
RCONFLICTS_${PN} = "python-tornado40"
RCONFLICTS_${PN} = "python-tornado50"
