#
#   Copyright (C) 2015 Pelagicore AB
#   All rights reserved.
#
python () {
    rrec = bb.data.getVar('RRECOMMENDS_packagegroup-placeholder-component-p1', d, 1)
    if rrec:
        rrec = rrec.replace("packagegroup-xserver-ivi", "")
        bb.data.setVar('RRECOMMENDS_packagegroup-placeholder-component-p1', rrec, d)
}
