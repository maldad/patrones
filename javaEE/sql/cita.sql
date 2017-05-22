CREATE TABLE cita(
    IDCITA INTEGER PRIMARY KEY NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    HORA TIME,
    FECHA DATE,
    PACIENTE INTEGER,
    CONSTRAINT FKIDPACIENTE FOREIGN KEY (PACIENTE) REFERENCES PACIENTE(IDPACIENTE)
)
