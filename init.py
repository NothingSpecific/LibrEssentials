#!/usr/bin/env python3

"""
This script initializes an Eclipse project based on detected system configuration and user input
Works with Spigot Builder: https://github.com/NothingSpecific/Spigot-Builder
"""

import re, getpass, os, random

old_home_dir=r"/home/[a-zA-Z0-9]+/"
new_home_dir="/home/" + getpass.getuser() + "/"

spigot_api_old=r"[\"\'][^\"\']+spigot-api-[^\"\'/]+-shaded.jar[\"\']"

def rewrite(filename, regex, substitution):
	f = open(filename, "r")
	data = f.read()
	f.close()
	data = re.sub(regex, substitution, data)
	f = open(filename, "w")
	f.write(data)
	f.close()

spigot_api_shaded = input("Path to spigot-api-shaded JAR: ")

if spigot_api_shaded == "":
	# If not specified, default to Spigot Builder's default shaded API JAR
	spigot_api_shaded = "~/mc/Spigot/Spigot-API/target/spigot-api-1.19.4-R0.1-SNAPSHOT-shaded.jar"

spigot_api_shaded = re.sub(old_home_dir, new_home_dir, spigot_api_shaded)
spigot_api_shaded = re.sub("^~/", new_home_dir, spigot_api_shaded)
spigot_api_shaded = re.sub("^./", os.getcwd() + "/", spigot_api_shaded)

if not os.path.isfile(spigot_api_shaded):
	print("Not a file or file doesn't exist: '%s'" %(spigot_api_shaded))
	raise SystemExit

if not re.match(spigot_api_old, "\"" + spigot_api_shaded + "\""):
	print("The provided Spigot API JAR cannot be matched programmatically. Future invocations of this init script will not be able to detect the old shaded API file.")
	yn=""
	while yn not in ["y", "n"]:
		yn = input("Continue? [y/n] ").lower()
		if yn == "n":
			raise SystemExit

rewrite(".classpath", old_home_dir, new_home_dir)
rewrite(".classpath", spigot_api_old, "\"" + spigot_api_shaded + "\"")

print("Done.")
