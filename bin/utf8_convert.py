import os

def convert_to_utf8(directory, original_encoding='ISO-8859-1'):
    for filename in os.listdir(directory):
        if filename.endswith(".java"):  # Example: change '.txt' to the file extension you're interested in
            filepath = os.path.join(directory, filename)
            with open(filepath, 'r', encoding=original_encoding) as file:
                content = file.read()

            with open(filepath, 'w', encoding='utf-8') as file:
                file.write(content)

            print(f"Converted {filename} to UTF-8")

# Usage
source_directory = 'C:\Users\AnastasiaPC\workspace\soft-devII-2024-project-material\soft-devII-2024-project-material\sales-commissions-application\src\gui'
convert_to_utf8(source_directory)


# Replace '/path/to/your/folder' with the path to your folder
# change_encoding(r'C:\Users\AnastasiaPC\workspace\soft-devII-2024-project-material\soft-devII-2024-project-material\sales-commissions-application\src\gui')
