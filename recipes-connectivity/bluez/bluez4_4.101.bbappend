#
#   Copyright (C) 2015 Pelagicore AB
#
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
SRC_URI += "file://bluetooth.conf \
	file://enable-audio-profiles.patch \
"

do_install_append() {
	mkdir -p ${D}/etc/systemd/system/
	mkdir -p ${D}/etc/systemd/system/bluetooth.target.wants/
	ln -s '/lib/systemd/system/bluetooth.service' '${D}/etc/systemd/system/dbus-org.bluez.service'
	ln -s '/lib/systemd/system/bluetooth.service' '${D}/etc/systemd/system/bluetooth.target.wants/bluetooth.service'
	install -m 0755 ${S}/test/simple-agent ${D}/usr/bin/bluez-simple-agent

}

PACKAGES += "${PN}-systemd"
FILES_${PN}-systemd += " \
		    /etc/systemd/system/bluetooth.target.wants/bluetooth.service \
		    /etc/systemd/system/dbus-org.bluez.service \
		    "
