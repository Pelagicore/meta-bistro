#
#   Copyright (C) 2015 Pelagicore AB
#
#   SPDX-License-Identifier: MIT
#
python () {
    rrec = d.getVar('RRECOMMENDS_packagegroup-placeholder-component-p1', d, 1)
    if rrec:
        rrec = rrec.replace("packagegroup-xserver-ivi", "")
        d.setVar('RRECOMMENDS_packagegroup-placeholder-component-p1', rrec, d)
}
