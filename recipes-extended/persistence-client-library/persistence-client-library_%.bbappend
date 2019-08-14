do_install_append() {
    install -d ${D}/Data/mnt-c/
    install -d ${D}/Data/mnt-wt/
}

FILES_${PN} += " \
    /Data/mnt-c \
    /Data/mnt-wt \
"
