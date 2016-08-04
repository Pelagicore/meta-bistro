#
#   Copyright (C) 2015 Pelagicore AB
#
inherit systemd

DEPENDS += "systemd"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://Data-mnt-c.mount"

do_install_append() {
    mkdir -p ${D}/Data/mnt-wt/
    mkdir ${D}/Data/mnt-c/
    install -d ${D}${systemd_unitdir}/system/
    install -d ${D}/etc/systemd/system/local-fs.target.wants/
    install -m 0644 ${S}/../Data-mnt-c.mount ${D}${systemd_unitdir}/system/Data-mnt\\x2dc.mount

    # Workaround for SYSTEMD_AUTO_ENABLE which didn't work with the escaped file name
    ln -s '${systemd_unitdir}/system/Data-mnt\x2dc.mount' '${D}/etc/systemd/system/local-fs.target.wants/Data-mnt\x2dc.mount'
}

FILES_${PN} =+ "\
    ${systemd_unitdir}/system/Data-mnt\\x2dc.mount \
    /etc/systemd/system/local-fs.target.wants/Data-mnt\\x2dc.mount \
"


# I had to add a workaround with `ln` for that, it never enabled the mount, perhaps
# because it has the escaped file name.

SYSTEMD_SERVICE_${PN} = "Data-mnt\x2dc.mount"
SYSTEMD_AUTO_ENABLE = "enable"