import os

def change_encoding(folder_path, original_encoding='windows-1252', new_encoding='utf-8'):
    for filename in os.listdir(folder_path):
        file_path = os.path.join(folder_path, filename)

        # Skip directories
        if os.path.isdir(file_path):
            continue

        try:
            with open(file_path, 'r', encoding=original_encoding) as file:
                content = file.read()

            with open(file_path, 'w', encoding=new_encoding) as file:
                file.write(content)
            print(f"Converted {filename}")
        except Exception as e:
            print(f"Error converting {filename}: {e}")

# Replace '/path/to/your/folder' with the path to your folder
change_encoding('C:\Users\AnastasiaPC\workspace\soft-devII-2024-project-material\soft-devII-2024-project-material\sales-commissions-application\src')
