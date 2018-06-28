DESCRIPTION = "DBus user session"
HOMEPAGE = "http://www.pelagicore.com"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302 \
                    file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

PR = "r0"

SRC_URI = "\
        file://dbus-session@.service \
"

S = "${WORKDIR}"

DEPENDS = "systemd dbus"

inherit systemd

do_install() {
    install -Dm0644 ${S}/dbus-session@.service \
    ${D}/${base_libdir}/systemd/system/dbus-session@.service
}

SYSTEMD_SERVICE_${PN}="dbus-session@.service"
SYSTEMD_AUTO_ENABLE = "disable"
