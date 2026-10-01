class SessaoJogo(
    val data: String,
    val horasJogadas: Double
)

class Jogo(
    val titulo: String,
    val codigoPlataforma: Int,
    val multiplayer: Boolean,
    val ehHistoria: Boolean,
    val horasParaFinalizar: Int = 40,
    var nomeAmigoEmprestimo: String? = null
) {
    private val sessoes = mutableListOf<SessaoJogo>()

    fun registrarSessao(sessao: SessaoJogo) {
        sessoes.add(sessao)
    }

    fun horasTotais(): Double {
        var total = 0.0
        for (sessao in sessoes) {
            total += sessao.horasJogadas
        }
        return total
    }

    fun plataforma(): String {
        return when (codigoPlataforma) {
            1 -> "PC"
            2 -> "Console"
            3 -> "Mobile"
            4 -> "Tabuleiro"
            else -> "Desconhecida"
        }
    }

    fun statusConclusao(): String {
        return if (horasTotais() > horasParaFinalizar && ehHistoria) {
            "Provavelmente Finalizado"
        } else {
            "Em andamento / Não finalizado"
        }
    }

    fun situacaoEmprestimo(): String {
        return nomeAmigoEmprestimo ?: "Na minha estante"
    }

    fun exibirInfo() {
        println("Título: $titulo")
        println("  Plataforma: ${plataforma()}")
        println("  Multiplayer: ${if (multiplayer) "Sim" else "Não"}")
        println("  Horas totais: ${horasTotais()}h")
        println("  Status: ${statusConclusao()}")
        println("  Emprestado para: ${situacaoEmprestimo()}")
        println()
    }
}

class Colecao {
    private val jogos = mutableListOf<Jogo>()

    fun adicionarJogo(jogo: Jogo) {
        jogos.add(jogo)
    }

    fun listarColecao() {
        println("===== MINHA COLEÇÃO DE JOGOS =====")
        for (jogo in jogos) {
            jogo.exibirInfo()
        }
    }

    fun horasTotaisColecao(): Double {
        var total = 0.0
        for (jogo in jogos) {
            total += jogo.horasTotais()
        }
        return total
    }
}

fun main() {
    val colecao = Colecao()

    val jogo1 = Jogo(
        titulo = "The Witcher 3",
        codigoPlataforma = 1,
        multiplayer = false,
        ehHistoria = true,
        nomeAmigoEmprestimo = "Carlos"
    )
    jogo1.registrarSessao(SessaoJogo("10/03/2025", 20.5))
    jogo1.registrarSessao(SessaoJogo("12/03/2025", 15.0))
    jogo1.registrarSessao(SessaoJogo("15/03/2025", 10.0))

    val jogo2 = Jogo(
        titulo = "FIFA 25",
        codigoPlataforma = 2,
        multiplayer = true,
        ehHistoria = false
    )
    jogo2.registrarSessao(SessaoJogo("11/03/2025", 3.0))
    jogo2.registrarSessao(SessaoJogo("14/03/2025", 2.5))

    val jogo3 = Jogo(
        titulo = "Catan",
        codigoPlataforma = 4,
        multiplayer = true,
        ehHistoria = false,
        horasParaFinalizar = 10
    )
    jogo3.registrarSessao(SessaoJogo("13/03/2025", 2.0))

    colecao.adicionarJogo(jogo1)
    colecao.adicionarJogo(jogo2)
    colecao.adicionarJogo(jogo3)

    colecao.listarColecao()
    println("Total de horas na coleção: ${colecao.horasTotaisColecao()}h")
}