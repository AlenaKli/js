#!/bin/bash

# Codebase Backup Script
# This script recursively traverses a codebase and saves all files into
# a single document with clear headers and footers indicating
# which parts correspond to which files.

# Define the output file
OUTPUT_FILE="codebase_backup.txt"

# Parse command line arguments
while [[ $# -gt 0 ]]; do
  case "$1" in
    -o|--output)
      OUTPUT_FILE="$2"
      shift 2
      ;;
    -h|--help)
      echo "Usage: $0 [OPTIONS]"
      echo "Options:"
      echo "  -o, --output FILE    Specify output file (default: codebase_backup.txt)"
      echo "  -h, --help           Display this help message"
      exit 0
      ;;
    *)
      echo "Unknown option: $1"
      echo "Use --help for usage information"
      exit 1
      ;;
  esac
done

# Clear the output file if it exists
> "$OUTPUT_FILE"

# Add a header to the output file
echo "# CODEBASE BACKUP" >> "$OUTPUT_FILE"
echo "# Generated on $(date)" >> "$OUTPUT_FILE"
echo "# Working directory: $(pwd)" >> "$OUTPUT_FILE"
echo -e "# ====================================\n\n" >> "$OUTPUT_FILE"

# Counter for file numbering
file_counter=1

# Find all files recursively, excluding hidden files/directories and common binary/non-code files
find . -type f \
    -not -path "*/\.*" \
    -not -path "*/node_modules/*" \
    -not -path "*/venv/*" \
    -not -path "*/__pycache__/*" \
    -not -path "*/build/*" \
    -not -path "*/dist/*" \
    -not -name "*.jpg" \
    -not -name "*.jpeg" \
    -not -name "*.png" \
    -not -name "*.gif" \
    -not -name "*.pdf" \
    -not -name "*.zip" \
    -not -name "*.tar" \
    -not -name "*.gz" \
    -not -name "*.pyc" \
    | sort | while read -r file; do

    # Skip the output file itself to avoid infinite loop
    if [ "$file" = "./$OUTPUT_FILE" ]; then
        continue
    fi

    # Skip binary files
    if file "$file" | grep -q "binary"; then
        continue
    fi

    # Get file extension to determine comment style
    file_ext="${file##*.}"

    # Determine comment style based on file extension
    comment_prefix="# "
    comment_suffix=""

    case "$file_ext" in
        js|ts|java|c|cpp|cs|go|swift|kt|scala|php)
            comment_prefix="// "
            ;;
        html|xml|svg)
            comment_prefix="<!-- "
            comment_suffix=" -->"
            ;;
        css|scss|sass|less)
            comment_prefix="/* "
            comment_suffix=" */"
            ;;
        sql)
            comment_prefix="-- "
            ;;
    esac

    # Get file metadata
    file_size=$(du -h "$file" | cut -f1)
    file_lines=$(wc -l < "$file" 2>/dev/null || echo "0")

    # Add a header comment with the file path and metadata
    echo -e "\n\n${comment_prefix}==================================================================${comment_suffix}" >> "$OUTPUT_FILE"
    echo -e "${comment_prefix}FILE #${file_counter}: $file${comment_suffix}" >> "$OUTPUT_FILE"
    echo -e "${comment_prefix}==================================================================${comment_suffix}" >> "$OUTPUT_FILE"
    echo -e "${comment_prefix}Path: $file${comment_suffix}" >> "$OUTPUT_FILE"
    echo -e "${comment_prefix}Size: $file_size | Lines: $file_lines${comment_suffix}" >> "$OUTPUT_FILE"
    echo -e "${comment_prefix}=== BEGIN CONTENT ===${comment_suffix}\n" >> "$OUTPUT_FILE"

    # Append the file contents
    cat "$file" >> "$OUTPUT_FILE"

    # Add a footer comment
    echo -e "\n${comment_prefix}=== END OF FILE #${file_counter}: $file ===${comment_suffix}" >> "$OUTPUT_FILE"
    echo -e "${comment_prefix}==================================================================${comment_suffix}" >> "$OUTPUT_FILE"

    # Increment file counter
    file_counter=$((file_counter + 1))
done

echo "Codebase has been backed up to $OUTPUT_FILE"
echo "Total files processed: $((file_counter - 1))"
