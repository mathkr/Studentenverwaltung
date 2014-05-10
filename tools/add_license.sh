# prepends the license to all java files below the cwd

# get the scripts directory
pushd .
DIR="$(cd "$(dirname "$0")" && pwd)"
popd

for i in `find -iname "*.java"`
do
	if ! grep -q Copyright $i
	then
		cat "$DIR/license_header_java" "$i" > "$i.tmplic" && mv "$i.tmplic" "$i"
	fi
done
