@import Persons.Page

@param Page page

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Persons</title>
</head>
<body>
<h2>Thin client</h2>
<ul>
    ${page.getStr()}
@for(var item : page.getPersons())
    <li>${item.getName()} ${item.getSurname()}</li>
@endfor
</ul>
</body>
</html>