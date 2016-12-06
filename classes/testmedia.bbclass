#
#   Copyright (C) 2016 Pelagicore AB
#
DESCRIPTION = "Helpers for installing different multimedia types"

# To use this class, inherit it, and specify the SRC_URI to an archive with
# media files. BitBake will do_unpack the archive, and this class file will
# search through it and find media files based on file extension. No specific
# file hierarchy is required in the archive. Nested archives are not supported.

# Known media types. Add extensions to create packages for the new extensions
FILEEXTENSIONS_AUDIO = "mp3 aac m4a ac3 ogg wma au mod wav flac"
FILEEXTENSIONS_VIDEO = "avi mov mpg wmv sid swf asf dat dv scf"
FILEEXTENSIONS_IMAGE = "png jpg gif"

# Add the corresponding variable above if adding new media types to this list.
# Removing from the list prevents packages of the given type to be built.
FILEEXTENSIONS_PACKS = "audio video image"

python () {
    import fnmatch

    def find_all_with_ext(dir, ext):
        # Recursively find all files of a a given extension
        files = []
        for root, dirnames, filenames in os.walk(dir):
            for filename in fnmatch.filter(filenames, '*.' + ext):
                files.append(os.path.join(root, filename))

        return files

    def get_used_extensions():
        exts = []
        for pack in d.getVar("FILEEXTENSIONS_PACKS", True).split(" "):
            pack = d.expand("${FILEEXTENSIONS_"+pack.upper()+"}").split()
            exts.extend(pack)

        # Remove extension with no files present
        used_exts = []
        for ext in exts:
            files = bb.utils.find_all_with_ext(d.expand("${WORKDIR}"), ext)
            if files:
                used_exts.append(ext)

        return used_exts

    # "monkeypatch export" the find_all_with_ext and get_used_extensions
    # functions via bb.utils
    bb.utils.find_all_with_ext = find_all_with_ext
    bb.utils.get_used_extensions = get_used_extensions
}

python do_install_media() {
    def install(file, dest, mode):
        os.system(d.expand("install -m %d '%s' '%s'" % (mode, f, dest)))

    extensions = bb.utils.get_used_extensions()
    for ext in extensions:
        files = bb.utils.find_all_with_ext(d.expand("${WORKDIR}"), ext)

        destdir = d.expand("${D}${datadir}/${PN}/%s/" % ext)
        bb.utils.mkdirhier(destdir)
        for f in files:
            install(f, destdir, 644)
}

python populate_packages_prepend () {
    # List extensions with files present
    def populated_exts_in_pack(pack):
        exts = []
        orig = d.getVar("FILEEXTENSIONS_%s" % pack.upper(), True).split(" ")
        existing = bb.utils.get_used_extensions()
        for o in orig:
            if o in existing:
                exts.append(o)

        return exts

    # Check if a 'pack' (audio, video, picture, etc) is empty
    def pack_empty(pack):
        return populated_exts_in_pack (pack) == []

    # Generate pack name and set RDEPENDS to extensions in pack
    def create_pack_with_rdepends(pack):
        pname = d.expand("${PN}-%s" % pack)
        rdeps = []
        exts = populated_exts_in_pack(pack)
        for ext in exts:
            rdeps.append(d.expand("${PN}-%s" % ext))

        d.setVar(d.expand("RDEPENDS_%s" % pname), " ".join(rdeps))
        d.setVar(d.expand("ALLOW_EMPTY_%s" % pname), "1")
        return pname

    # Create packages for all different populated extensions
    newpackages = []
    ftypes = bb.utils.get_used_extensions()
    for ftype in ftypes:
        newpackages.append(d.expand('${PN}-' + ftype))
        d.setVar(d.expand('FILES_${PN}-' + ftype),
                 d.expand('${datadir}/${PN}/' + ftype))

    # Create virtual packages for extension packs (audio, video, etc)
    extensionpacks = d.getVar("FILEEXTENSIONS_PACKS", True).split(" ")
    for pack in extensionpacks:
        if not pack_empty(pack):
            newpackages.append(create_pack_with_rdepends(pack))

    d.setVar('PACKAGES', " ".join(newpackages))
}

addtask do_install_media after do_install before do_package
