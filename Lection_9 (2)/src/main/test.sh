#!/bin/bash

# Output file (default)
OUTPUT_FILE="all_code_files.txt"

# Extensions to include (customize as needed)
CODE_EXTENSIONS=("*.c" "*.cpp" "*.py" "*.js" "*.java" "*.ts" "*.rb" "*.go" "*.php" "*.html" "*.css" "*.sh")

# Clear or create the output file
> "$OUTPUT_FILE"

# Loop over each extension
for ext in "${CODE_EXTENSIONS[@]}"; do
    # Find files matching extension
    while IFS= read -r -d '' file; do
        # Add delimiter with relative path
        echo "========== $file ==========" >> "$OUTPUT_FILE"
        cat "$file" >> "$OUTPUT_FILE"
        echo -e "\n" >> "$OUTPUT_FILE"
    done < <(find . -type f -name "$ext" -print0)
done

echo "All code files saved to $OUTPUT_FILE"
